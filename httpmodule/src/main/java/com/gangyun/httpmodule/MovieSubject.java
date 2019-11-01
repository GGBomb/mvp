package com.gangyun.httpmodule;

import java.util.List;

/*
 */
public class MovieSubject {
    private int count;
    private int start;
    private int total;
    private List<Movie> subjects;
    //{"count": 1, "start": 0, "total": 250, "subjects": [{"rating": {"max": 10, "average": 9.6, "stars": "50", "min": 0}, "genres": ["\u72af\u7f6a", "\u5267\u60c5"], "title": "\u8096\u7533\u514b\u7684\u6551\u8d4e", "casts": [{"alt": "https:\/\/movie.douban.com\/celebrity\/1054521\/", "avatars": {"small": "https://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p17525.webp", "large": "https://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p17525.webp", "medium": "https://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p17525.webp"}, "name": "\u8482\u59c6\u00b7\u7f57\u5bbe\u65af", "id": "1054521"}, {"alt": "https:\/\/movie.douban.com\/celebrity\/1054534\/", "avatars": {"small": "https://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p34642.webp", "large": "https://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p34642.webp", "medium": "https://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p34642.webp"}, "name": "\u6469\u6839\u00b7\u5f17\u91cc\u66fc", "id": "1054534"}, {"alt": "https:\/\/movie.douban.com\/celebrity\/1041179\/", "avatars": {"small": "https://img1.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p5837.webp", "large": "https://img1.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p5837.webp", "medium": "https://img1.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p5837.webp"}, "name": "\u9c8d\u52c3\u00b7\u5188\u987f", "id": "1041179"}], "collect_count": 1811751, "original_title": "The Shawshank Redemption", "subtype": "movie", "directors": [{"alt": "https:\/\/movie.douban.com\/celebrity\/1047973\/", "avatars": {"small": "https://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p230.webp", "large": "https://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p230.webp", "medium": "https://img3.doubanio.com\/view\/celebrity\/s_ratio_celebrity\/public\/p230.webp"}, "name": "\u5f17\u5170\u514b\u00b7\u5fb7\u62c9\u90a6\u7279", "id": "1047973"}], "year": "1994", "images": {"small": "https://img3.doubanio.com\/view\/photo\/s_ratio_poster\/public\/p480747492.webp", "large": "https://img3.doubanio.com\/view\/photo\/s_ratio_poster\/public\/p480747492.webp", "medium": "https://img3.doubanio.com\/view\/photo\/s_ratio_poster\/public\/p480747492.webp"}, "alt": "https:\/\/movie.douban.com\/subject\/1292052\/", "id": "1292052"}], "title": "\u8c46\u74e3\u7535\u5f71Top250"}

    public MovieSubject() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Movie> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Movie> subjects) {
        this.subjects = subjects;
    }
}
