//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package deutschebank.thebeans;

import deutschebank.MainUnit;
import deutschebank.dbutils.DBConnector;
import deutschebank.dbutils.PropertyLoader;
import deutschebank.dbutils.User;
import deutschebank.dbutils.UserHandler;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationScopeHelper {
    private String itsInfo = "NOT SET";
    private DBConnector itsConnector = null;

    public ApplicationScopeHelper() {
    }

    public String getInfo() {
        return this.itsInfo;
    }

    public void setInfo(String itsInfo) {
        this.itsInfo = itsInfo;
    }

    public boolean bootstrapDBConnection() {
        boolean result = false;

        try {
            this.itsConnector = DBConnector.getConnector();
            PropertyLoader pLoader = PropertyLoader.getLoader();
            Properties pp = pLoader.getPropValues("dbConnector.properties");
            result = this.itsConnector.connect(pp);
        } catch (IOException var4) {
            Logger.getLogger(ApplicationScopeHelper.class.getName()).log(Level.SEVERE, (String)null, var4);
        }

        return result;
    }

    public User userLogin(String userId, String userPwd) {
        User theUser = null;
        UserHandler theUserHandler = UserHandler.getLoader();
        theUser = theUserHandler.loadFromDB(this.itsConnector.getConnection(), userId, userPwd);
        if(theUser != null) {
            MainUnit.log("User " + userId + " has logged into system");
        }

        return theUser;
    }
}
