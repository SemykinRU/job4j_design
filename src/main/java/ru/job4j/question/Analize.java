package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted;
        Map<Integer, String> userMap = new HashMap<>();
        for (var itemPrev : previous) {
            userMap.put(itemPrev.getId(), itemPrev.getName());
        }
        for (var itemCurr : current) {
            if (!userMap.containsKey(itemCurr.getId())) {
                added++;
            } else if (!Objects.equals(userMap.get(itemCurr.getId()), itemCurr.getName())) {
                changed++;
            }
            userMap.remove(itemCurr.getId());
        }
        deleted = userMap.size();
        return new Info(added, changed, deleted);
    }
}