package pasantia.backend.entity;

import jakarta.persistence.Entity;

@Entity
public class Students extends People{

    private String cv, interestCarrer;

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getInterestCarrer() {
        return interestCarrer;
    }

    public void setInterestCarrer(String interestCarrer) {
        this.interestCarrer = interestCarrer;
    }
}
