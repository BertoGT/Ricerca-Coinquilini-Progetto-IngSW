/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessModel;

import Database.Database;
import java.sql.SQLException;

/**
 *
 * @author alberto
 */
public class BusinnessModelAnnuncio {
    private Database db;

    public BusinnessModelAnnuncio() throws SQLException {
        db = new Database();
    }
}
