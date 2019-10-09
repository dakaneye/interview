package com.sam.dacanay.interview.phone;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneSolution {
    public static final Map<Character, String> digitToChar = new HashMap<>();

    // I looked up what a normal phone dialpad looked like IRL and 1 didn't actually normally point to any letters
    static {
        digitToChar.put('1', "");
        digitToChar.put('2', "abc");
        digitToChar.put('3', "def");
        digitToChar.put('4', "ghi");
        digitToChar.put('5', "jkl");
        digitToChar.put('6', "mno");
        digitToChar.put('7', "pqrs");
        digitToChar.put('8', "tuv");
        digitToChar.put('9', "wxyz");
    }

    public static void main(String[] args) {

    }

    public static List<String> getValidWords(String phone, String appId, String appKey) {
        List<String> words = new ArrayList<>();
        for (int i = 0; i < phone.length(); i++) {
            char currentDigit = phone.charAt(i);
            String result = digitToChar.get(currentDigit);
            if (result == null || result.equals("")) {
                continue;
            }

            String[] resultParts = result.split("");
            if (words.size() == 0) {
                words.addAll(Arrays.asList(resultParts));
                continue;
            }
            List<String> tmp = new ArrayList<>();
            for (String option : resultParts) {
                for (String existing : words) {
                    tmp.add(existing.concat(option));
                }
            }
            words = tmp;
        }

        if (appId == null || appId.equals("") || appKey == null || appKey.equals("")) {
            return words;
        }
        WordsValidator validator = new WordsValidator(words, appId, appKey);
        return validator.getValidatedWords();
    }

    static class WordsValidator {
        private List<String> unvalidatedWords;
        private OkHttpClient client = new OkHttpClient();
        private String appId;
        private String appKey;

        public WordsValidator(List<String> unvalidatedWords, String appId, String appKey) {
            this.unvalidatedWords = unvalidatedWords;
            this.appId = appId;
            this.appKey = appKey;
        }

        public List<String> getValidatedWords() {
            // I found an API at https://developer.oxforddictionaries.com/documentation#!/Search/get_search_translations_source_lang_search_target_lang_search
            // That lets you search for a word, but it's restricted for free accounts. This method would call that API to search for a word,
            // If one was found, then it would save it to a new list of words that was actually valid
            List<String> validated = new ArrayList<>();
            for (String unvalidated : unvalidatedWords) {
                HttpUrl url = HttpUrl.parse("https://od-api.oxforddictionaries.com/api/v2/search/translations/en/es")
                    .newBuilder()
                    .addQueryParameter("q", unvalidated)
                    .build();
                Request request = new Request.Builder().url(url)
                    .addHeader("Accept", "text/plain")
                    .addHeader("app_id", appId)
                    .addHeader("app_key", appKey)
                    .build();
                try(Response resp = client.newCall(request).execute()) {
                    if (resp.isSuccessful()) {
                        validated.add(unvalidated);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return validated;
        }
    }
}
