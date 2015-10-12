package any.tv.mobile.gamerstm.models;

/**
 * Created by adin234 on 28/09/2015.
 */
public class GamersTranslation extends BaseModel {
    private String language;
    private String translation;

    public GamersTranslation() {}

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
