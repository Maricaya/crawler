package com.github.hcsp;

import java.sql.SQLException;

public interface CrawlerDao {
    String getNextLinkThenDelete() throws SQLException;

//    void updateDatabase(String link, String sql) throws SQLException;

    void insertNewsIntoDatabase(String url, String title, String content) throws SQLException;

    boolean isLinkProcessed(String link) throws SQLException;

    void insertLinkToBeProcessed(String href);

    void insertProcessedLink(String link);
}
