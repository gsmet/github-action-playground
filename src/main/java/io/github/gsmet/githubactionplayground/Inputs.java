package io.github.gsmet.githubactionplayground;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class Inputs {

    private static final String INPUT_PREFIX = "INPUT_";
    private static final String ACTION = "action";
    private static final String GITHUB_TOKEN = "github-token";

    private final Map<String, String> inputs = new HashMap<>();

    public Inputs() {
        for (Entry<String, String> envEntry : System.getenv().entrySet()) {
            if (!envEntry.getKey().startsWith(INPUT_PREFIX)) {
                continue;
            }

            inputs.put(envEntry.getKey().substring(INPUT_PREFIX.length()).toLowerCase(Locale.ROOT), envEntry.getValue());
        }
    }

    public String get(String key) {
        if (!inputs.containsKey(key)) {
            throw new IllegalArgumentException(key + " is not a valid input");
        }

        return inputs.get(key);
    }

    public Optional<String> getAction() {
        return Optional.ofNullable(inputs.get(ACTION));
    }

    public Optional<String> getGitHubToken() {
        return Optional.ofNullable(inputs.get(GITHUB_TOKEN));
    }
}
