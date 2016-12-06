package dataaccess;


import models.OrderLineMapper;
import models.OrderMapper;
import models.ShopItemMapper;
import models.UserMapper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 * 
/**
 *
 * @author freyb
 */
public class DataFacade {

    private UserMapper um;
    private OrderMapper om;
    private OrderLineMapper olm;
    private ShopItemMapper sim;

    public DataFacade() {
        this.um = new UserMapper();
        this.om = new OrderMapper();
        this.olm = new OrderLineMapper();
        this.sim = new ShopItemMapper();
    }
    
    /*
    * http://www.dofactory.com/net/facade-design-pattern 
    *
    * Dette er vores DataAccess Object som tager sig af alt og holder styr på alle mapper klasserne
    * Læs evt på overstående link hvorfor dette er smart. Thomas bruger også dette i hans Kayak Booking.
    *
    * Her skal alle de metoder som vi for brug for kaldes via deres mapperObjekter.
    * Gå gerne i gang med at lave dem efterhånden som i får brug for dem.
    *
    * 
    */
}
