package ricciliao.bsm.rest.dto;


import ricciliao.x.component.cache.pojo.CacheDto;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class EmailCacheDto extends CacheDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 8769005261320201830L;

    private String from;
    private String to;
    private String typeCd;
    private String subject;
    private String title;
    private boolean sent = false;
    private LocalDateTime sentDtm;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTypeCd() {
        return typeCd;
    }

    public void setTypeCd(String typeCd) {
        this.typeCd = typeCd;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean send) {
        this.sent = send;
    }

    public LocalDateTime getSentDtm() {
        return sentDtm;
    }

    public void setSentDtm(LocalDateTime sentDtm) {
        this.sentDtm = sentDtm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailCacheDto that)) return false;
        if (!super.equals(o)) return false;
        return isSent() == that.isSent() && Objects.equals(getFrom(), that.getFrom()) && Objects.equals(getTo(), that.getTo()) && Objects.equals(getTypeCd(), that.getTypeCd()) && Objects.equals(getSubject(), that.getSubject()) && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getSentDtm(), that.getSentDtm());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getFrom(), getTo(), getTypeCd(), getSubject(), getTitle(), isSent(), getSentDtm());
    }
}
