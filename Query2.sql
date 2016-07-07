select `Orders`.`DateOfOrder`,sum(`Orders`.`QuantityOrdered`*(`Product`.`SellingPrice`-`Product`.`Cost`)) from `cnu2016_mohana_kumar`.`Product`
INNER JOIN `cnu2016_mohana_kumar`.`Order_has_Product` ON `Product`.`ProductId`=`Order_has_Product`.`Product_ProductId`
INNER JOIN `cnu2016_mohana_kumar`.`Orders` ON `Order_has_Product`.`Order_OrderId`=`Orders`.`OrderId` GROUP BY `Orders`.`DateOfOrder`;
