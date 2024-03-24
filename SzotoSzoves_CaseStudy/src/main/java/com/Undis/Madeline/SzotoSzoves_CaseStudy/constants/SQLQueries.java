package com.Undis.Madeline.SzotoSzoves_CaseStudy.constants;

public class SQLQueries {
    public static final String GET_FLAGGED_WORDS = "select * from user_word where flagged = true and user_id = ?";
}
