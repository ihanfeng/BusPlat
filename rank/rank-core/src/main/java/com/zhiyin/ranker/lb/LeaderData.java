package com.zhiyin.ranker.lb;

public class LeaderData {

    private String _member;
    private Double _score;
    private Long _rank;

    /**
     * Store leader data
     *
     * @param member Name
     * @param score  Score
     * @param rank   Rank
     */
    public LeaderData(String member, double score, long rank) {
        _member = member;
        _score = score;
        _rank = rank;
    }

    /**
     * Get the member name
     *
     * @return Member name
     */
    public String getMember() {
        return _member;
    }

    /**
     * Set the member name
     *
     * @param member Member name
     */
    public void setMember(String member) {
        _member = member;
    }

    /**
     * Get the score
     *
     * @return Score
     */
    public double getScore() {
        return _score;
    }

    /**
     * Set the score
     *
     * @param score Score
     */
    public void setScore(double score) {
        _score = score;
    }

    /**
     * Get the rank
     *
     * @return Rank
     */
    public long getRank() {
        return _rank;
    }

    /**
     * Set the rank
     *
     * @param rank Rank
     */
    public void setRank(long rank) {
        _rank = rank;
    }
}
