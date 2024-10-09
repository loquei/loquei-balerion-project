package com.loquei.core.infrastructure.config.json;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonConfig {

    @Bean
    public GsonBuilder gsonBuilder(List<GsonBuilderCustomizer> customizers) {

        final GsonBuilder builder = new GsonBuilder();

        customizers.forEach(c -> c.customize(builder));

        builder.registerTypeHierarchyAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {

            final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            @Override
            public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
                return new JsonPrimitive(DATE_TIME_FORMATTER.format(src));
            }
        });

        builder.registerTypeHierarchyAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {

            final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
                return new JsonPrimitive(DATE_FORMATTER.format(src));
            }
        });

        builder.registerTypeHierarchyAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {

            final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                return LocalDate.parse(json.getAsString(), DATE_FORMATTER);
            }
        });

        builder.registerTypeHierarchyAdapter(Instant.class, new TypeAdapter<Instant>() {
            private final DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;

            @Override
            public void write(JsonWriter out, Instant value) throws IOException {
                if (value == null) out.nullValue();
                else out.value(formatter.format(value));
            }

            @Override
            public Instant read(JsonReader in) throws IOException {
                if (in.peek() == com.google.gson.stream.JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                return Instant.parse(in.nextString());
            }
        });

        builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);

        return builder;
    }
}
