package com.whh.i18n;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class YamlTranslations {

    private static final Yaml YAML = new Yaml();

    private YamlTranslations() {}

    public static Map<String, String> load(String lang, String modId) {
        // 优先当前语言，其次回退英文
        Map<String, String> m = read("/assets/" + modId + "/carpet/lang/" + lang + ".yml");
        if (m.isEmpty() && !"en_us".equalsIgnoreCase(lang)) {
            m = read("/assets/" + modId + "/carpet/lang/en_us.yml");
        }
        return m;
    }

    @SuppressWarnings("unchecked")
    private static Map<String, String> read(String path) {
        try (InputStream in = YamlTranslations.class.getResourceAsStream(path)) {
            if (in == null) return Collections.emptyMap();
            Object raw = YAML.load(new java.io.InputStreamReader(in, StandardCharsets.UTF_8));
            if (raw instanceof Map<?, ?> map) {
                // 支持“扁平写法”（key: value）和“嵌套写法”（rule: ... category: ...）
                Map<String, String> out = new HashMap<>();
                flatten("", (Map<String, Object>) map, out);
                return out;
            }
        } catch (Exception ignored) {}
        return Collections.emptyMap();
    }

    private static void flatten(String prefix, Map<?, ?> src, Map<String, String> out) {
        for (Map.Entry<?, ?> e : src.entrySet()) {
            String key = prefix.isEmpty() ? e.getKey().toString() : prefix + "." + e.getKey();
            Object v = e.getValue();
            if (v instanceof Map<?, ?> nested) {
                flatten(key, nested, out);
            } else if (v != null) {
                out.put(key, v.toString());
            }
        }
    }



}
