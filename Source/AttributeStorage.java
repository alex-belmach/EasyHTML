package sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Livorni on 11.11.2015.
 */

public class AttributeStorage {
    private static AttributeStorage instance;
    Map<String, ArrayList<String>> attributeMap;

    public static AttributeStorage getInstance() {
        if (instance == null) {
            instance = new AttributeStorage();
        }
        return instance;
    }

    private AttributeStorage () {
        attributeMap = new HashMap<>();
        attributeMap.put("html", new ArrayList<>(1));
        attributeMap.get("html").add("title");

        attributeMap.put("body", new ArrayList<>(4));
        attributeMap.get("body").add("link");
        attributeMap.get("body").add("background");
        attributeMap.get("body").add("scroll");
        attributeMap.get("body").add("text");

        attributeMap.put("h1", new ArrayList<>(1));
        attributeMap.get("h1").add("align");

        attributeMap.put("h2", new ArrayList<>(1));
        attributeMap.get("h2").add("align");

        attributeMap.put("h3", new ArrayList<>(1));
        attributeMap.get("h3").add("align");

        attributeMap.put("h4", new ArrayList<>(1));
        attributeMap.get("h4").add("align");

        attributeMap.put("h5", new ArrayList<>(1));
        attributeMap.get("h5").add("align");

        attributeMap.put("h6", new ArrayList<>(1));
        attributeMap.get("h6").add("align");

        attributeMap.put("link", new ArrayList<>(4));
        attributeMap.get("link").add("href");
        attributeMap.get("link").add("rel");
        attributeMap.get("link").add("type");
        attributeMap.get("link").add("media");

        attributeMap.put("ol", new ArrayList<>(3));
        attributeMap.get("ol").add("type");
        attributeMap.get("ol").add("start");
        attributeMap.get("ol").add("Number of elements");

        attributeMap.put("ul", new ArrayList<>(2));
        attributeMap.get("ul").add("type");
        attributeMap.get("ul").add("Number of elements");

        attributeMap.put("table", new ArrayList<>(6));
        attributeMap.get("table").add("cols");
        attributeMap.get("table").add("height");
        attributeMap.get("table").add("width");
        attributeMap.get("table").add("cellpadding");
        attributeMap.get("table").add("cellspacing");
        attributeMap.get("table").add("frame");

        attributeMap.put("a", new ArrayList<>(5));
        attributeMap.get("a").add("href");
        attributeMap.get("a").add("rel");
        attributeMap.get("a").add("type");
        attributeMap.get("a").add("media");
        attributeMap.get("a").add("download");

        attributeMap.put("common", new ArrayList<>(2));
        attributeMap.get("common").add("class");
        attributeMap.get("common").add("id");
    }

    protected ArrayList<String> getAttributesList(String tagName) {
        return attributeMap.get(tagName);
    }

}
