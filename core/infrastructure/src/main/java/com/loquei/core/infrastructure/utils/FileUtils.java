package com.loquei.core.infrastructure.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public final class FileUtils {

    private FileUtils() {}

    public static String extractFileName(final MultipartFile multipartFile) {
        return StringUtils.cleanPath(multipartFile.getOriginalFilename());
    }

    public static String extractFileType(final MultipartFile multipartFile) {
        return multipartFile.getContentType();
    }

    public static byte[] extractFileContent(final MultipartFile multipartFile) {
        try {
            return multipartFile.getBytes();
        } catch (final Exception e) {
            throw new RuntimeException("Failed to extract content from file", e);
        }
    }
}
