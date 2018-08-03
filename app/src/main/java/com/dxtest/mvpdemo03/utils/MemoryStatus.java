package com.dxtest.mvpdemo03.utils;

import java.io.File;

import android.os.Environment;
import android.os.StatFs;

/**
 * Collection of methods for operate memory.
 */
public class MemoryStatus {

    static final int ERROR = -1;

    private static final long RESERVED_SIZE = 2*1024*1024;//1024B * 1024k * 2M

    public static long getTotalExternalMemorySize() {
        if (isExternalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();
            return totalBlocks * blockSize;
        } else {
            return ERROR;
        }
    }

     public static long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }

    public static boolean isSpecificMemoryAvailable(long size, String path) {
        long availableMemory = getSpecificMemoryAvaliable(path);
        return (size <= availableMemory);
    }

     public static long getSpecificMemoryAvaliable(String path) {
        StatFs stat = new StatFs(path);
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }


//   方法一      -------start
    public static boolean isMemoryAvailable(long size) {
        size += RESERVED_SIZE;
        if (isExternalMemoryAvailable()) {
            return isExternalMemoryAvailable(size);
        }
        return isInternalMemoryAvailable(size);
    }

    public static boolean isExternalMemoryAvailable(long size) {
        long availableMemory = getAvailableExternalMemorySize();
        return !(size > availableMemory);
    }

    public static long getAvailableExternalMemorySize() {
        if (isExternalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return availableBlocks * blockSize;
        } else {
            return ERROR;
        }
    }

     public static boolean isInternalMemoryAvailable(long size) {
        long availableMemory = getAvailableInternalMemorySize();
        return !(size > availableMemory);
    }

    public static long getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }
//   方法一      -------end

    public static String formatSize(long size) {
        String suffix = "B";

        if (size >= 1024) {
            suffix = "KiB";
            size /= 1024;
            if (size >= 1024) {
                suffix = "MiB";
                size /= 1024;
            }
        }

        StringBuilder resultBuffer = new StringBuilder(Long.toString(size));

        int commaOffset = resultBuffer.length() - 3;
        while (commaOffset > 0) {
            resultBuffer.insert(commaOffset, ',');
            commaOffset -= 3;
        }

        if (suffix != null) {
            resultBuffer.append(suffix);
        }
        return resultBuffer.toString();
    }

    public static boolean isExternalMemoryAvailable() {
        return android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
    }

}
