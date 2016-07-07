START TRANSACTION;

#update `cnu2016_mohana_kumar`.`Inventory`  set `Inventory`.`Quantity`=`Inventory`.`Quantity`;
SET @quantity=(Select `Orders`.`QuantityOrdered` from
 `cnu2016_mohana_kumar`.`Orders`,
 `cnu2016_mohana_kumar`.`Order_has_Product`,
 `cnu2016_mohana_kumar`.`Product`
 
where 

`Orders`.`OrderId`=`cnu2016_mohana_kumar`.`Order_has_Product`.`Order_OrderId` 
and `Order_has_Product`.`Product_ProductId`=`cnu2016_mohana_kumar`.`Product`.`ProductId` 
and `Orders`.`OrderId`=123) ;

SET @id=(Select `Product`.`ProductId` from 
 `cnu2016_mohana_kumar`.`Orders`,
 `cnu2016_mohana_kumar`.`Order_has_Product`,
 `cnu2016_mohana_kumar`.`Product`
where 

`Orders`.`OrderId`=`cnu2016_mohana_kumar`.`Order_has_Product`.`Order_OrderId` 
and `Order_has_Product`.`Product_ProductId`=`cnu2016_mohana_kumar`.`Product`.`ProductId` 
and `Orders`.`OrderId`=123) ;

update `cnu2016_mohana_kumar`.`Inventory`  set `Inventory`.`Quantity`=`Inventory`.`Quantity`- @quantity where `Inventory`.`ProductId`= @id ;

#update 

COMMIT;
                    
                    