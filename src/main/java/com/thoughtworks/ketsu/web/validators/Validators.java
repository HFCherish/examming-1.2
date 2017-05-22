package com.thoughtworks.ketsu.web.validators;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.BadRequestException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by pzzheng on 5/22/17.
 */
public class Validators {

    public static void validate(Map<String, Object> info, Validator validator) {
        Optional<String> errors = validator.validate(info);

        if (errors.isPresent())
            throw new BadRequestException(errors.get());
    }

    public static Validator fieldNotEmpty(String field) {
        return info -> info.getOrDefault(field, "").toString().isEmpty() ? Optional.of(fieldErrorMessage(field, field + " cannot be empty").toString()) : Optional.empty();
    }

    public static Validator fieldIsEnum(Class enumClass, String field) {
        return info -> EnumUtils.isValidEnum(enumClass, info.getOrDefault(field, "").toString()) ?
                Optional.empty() :
                Optional.of(fieldErrorMessage(field, field + " is not valid. Valid values are: " + EnumUtils.getEnumList(enumClass).toString()).toString());
    }

    public static Validator all(Validator... validators) {
        return info -> {
            List<String> errors = Arrays.asList(validators).stream()
                    .map(validator -> validator.validate(info))
                    .filter(error -> error.isPresent())
                    .map(error -> error.get())
                    .collect(Collectors.toList());

            return errors.size() > 0 ?
                    Optional.of(StringUtils.join(errors, "\n")) :
                    Optional.empty();
        };
    }


    private static Map<String, String> fieldErrorMessage(String field, String message) {
        return new HashMap() {{
            put("field", field);
            put("message", message);
        }};
    }
}
