package ru.rezonans_lab.lordofsands.ui;

import android.content.Context;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import ru.rezonans_lab.lordofsands.R;

public class ResourceMapper {
    private static ResourceMapper instance;
    private final Map<Float, Integer> imageMap = new HashMap<>();
    private static final int DEFAULT_IMAGE = R.drawable.loes_test_pic;

    private ResourceMapper(Context context) {
        loadMapping(context);
    }

    public static ResourceMapper getInstance(Context context) {
        if (instance == null) {
            instance = new ResourceMapper(context.getApplicationContext());
        }
        return instance;
    }

    private void loadMapping(Context context) {
        try {
            InputStream is = context.getResources().openRawResource(R.raw.resource_mapping);
            JsonNode root = new ObjectMapper().readTree(is);

            for (JsonNode node : root) {
                float chapterId = (float) node.get("chapterId").asDouble();
                String imageName = node.get("image").asText();
                int resId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
                imageMap.put(chapterId, resId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getImageIdForChapter(float chapterId) {
        Integer resId = imageMap.get(chapterId);
        return resId != null ? resId : DEFAULT_IMAGE;
    }
}