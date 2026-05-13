package com.matharsa.enterprise.logistics.content_aggregator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * The Article record acts as our immutable data blueprint.
 * It implements Comparable so Java knows how to automatically sort articles by time.
 */
record Article(String title, String category, LocalDateTime publishedAt) implements Comparable<Article> {

    // Formats the timestamp to a human-readable string showing hours, minutes, and seconds
    public String getFormattedTime() {
        return publishedAt.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    /**
     * Core Sorting Rule: Compares this article's timestamp against another.
     * Reversing the default comparison order ensures that newer items rise to the top of the list.
     */
    @Override
    public int compareTo(Article o) {
        return o.publishedAt.compareTo(this.publishedAt);
    }

    // Defines how the article prints out text elements inside our UI list view
    @Override
    public String toString() {
        return "[" + category.toUpperCase() + "] " + title + " (" + getFormattedTime() + ")";
    }
}

/**
 * The Logic Engine layer.
 * This class hosts our collection database and manages processing and sorting behaviors.
 */
public class aggregatorManager {
    // Our active in-memory list structure used to accumulate individual article instances
    private final List<Article> feed = new ArrayList<>();

    /**
     * Creates a fresh article with a precise timestamp, stores it inside our list,
     * and forces an instant re-sort so the timeline display updates flawlessly.
     */
    public void addArticle(String title, String category) {
        feed.add(new Article(title, category, LocalDateTime.now()));
        Collections.sort(feed); // Automatically activates our compareTo chronological ordering logic
    }

    // Provides access to our sorted collection list so the GUI layer can read it
    public List<Article> getFeed() {
        return feed;
    }
}

