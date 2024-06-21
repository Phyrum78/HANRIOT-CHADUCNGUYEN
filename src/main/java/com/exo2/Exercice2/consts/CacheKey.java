package com.exo2.Exercice2.consts;

public class CacheKey {
    private static final String findAll = "findAll";
    private static final String findById = "findById";

    public static final String findAllUser = findAll + "User";
    public static final String findByIdUser = findById + "User";

    public static final String findAllReview = findAll + "Review";
    public static final String findByIdReview = findById + "Review";

    public static final String findAllParty = findAll + "Party";
    public static final String findByIdParty = findById + "Party";
}