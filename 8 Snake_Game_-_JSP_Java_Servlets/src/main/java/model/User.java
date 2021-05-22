package model;

import java.sql.Time;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class User {

    private String user_id;
    private String username;
    private Long time_spent; // time spent in milliseconds
    private Integer high_score;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, Long time_spent) {
        this.username = username;
        this.time_spent = time_spent;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                "time_spent='" + time_spent + '\'' +
                "high_score='" + high_score + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User profile = (User) o;
        return username.equals(profile.username) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, username, time_spent);
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getTime_spent() {
        return time_spent;
    }

    public void setTime_spent(Long time_spent) {
        this.time_spent = time_spent;
    }

    public Integer getHigh_score() {
        return high_score;
    }

    public void setHigh_score(Integer high_score) {
        this.high_score = high_score;
    }

    public Time getTimeObjForTimeSpent(){
        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(time_spent),
                TimeUnit.MILLISECONDS.toMinutes(time_spent) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(time_spent) % TimeUnit.MINUTES.toSeconds(1));
        return Time.valueOf(hms);
    }
}
