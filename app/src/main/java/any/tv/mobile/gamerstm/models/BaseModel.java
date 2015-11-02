package any.tv.mobile.gamerstm.models;

import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by adin234 on 21/09/2015.
 */
public class BaseModel extends SugarRecord implements Serializable {
    protected Date createdDate;
    protected transient long id;

    public long save() {
        createdDate = new Date();
        return super.save();
    }
}
