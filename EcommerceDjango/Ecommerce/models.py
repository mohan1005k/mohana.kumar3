# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey has `on_delete` set to the desired behavior.
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from __future__ import unicode_literals

from django.db import models


class Brand(models.Model):
    brandname = models.CharField(db_column='BrandName', primary_key=True, max_length=100)  # Field name made lowercase.
    branddescription = models.CharField(db_column='BrandDescription', max_length=200, blank=True, null=True)  # Field name made lowercase.
    productid = models.IntegerField(db_column='ProductId')  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'Brand'


class Categories(models.Model):
    description = models.CharField(db_column='Description', max_length=45, blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'Categories'


class Category(models.Model):
    description = models.CharField(db_column='Description', max_length=200, blank=True, null=True)  # Field name made lowercase.
    categoryid = models.AutoField(db_column='CategoryId', primary_key=True)  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'Category'


class Feedback(models.Model):
    description = models.CharField(db_column='Description', max_length=300, blank=True, null=True)  # Field name made lowercase.
    email = models.CharField(db_column='Email', max_length=45, blank=True, null=True)  # Field name made lowercase.
    type = models.CharField(db_column='Type', max_length=45, blank=True, null=True)  # Field name made lowercase.
    feedbackdate = models.DateTimeField(db_column='FeedbackDate', blank=True, null=True)  # Field name made lowercase.
    status = models.CharField(db_column='Status', max_length=45, blank=True, null=True)  # Field name made lowercase.
    userid = models.ForeignKey('User', models.DO_NOTHING, db_column='UserId', blank=True, null=True)  # Field name made lowercase.
    feedbackid = models.AutoField(db_column='FeedbackId', primary_key=True)  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'Feedback'


class Inventory(models.Model):
    quantity = models.IntegerField(db_column='Quantity', blank=True, null=True)  # Field name made lowercase.
    productid = models.ForeignKey('Product', models.DO_NOTHING, db_column='ProductId')  # Field name made lowercase.
    quantityid = models.IntegerField(db_column='quantityId')  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'Inventory'


class OrderHasProduct(models.Model):
    orderid = models.ForeignKey('Orders', models.DO_NOTHING, db_column='OrderId')  # Field name made lowercase.
    productid = models.ForeignKey('Product', models.DO_NOTHING, db_column='ProductId')  # Field name made lowercase.
    quantityordered = models.IntegerField(db_column='QuantityOrdered', blank=True, null=True)  # Field name made lowercase.
    price = models.FloatField(db_column='Price', blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'Order_has_Product'
        unique_together = (('orderid', 'productid'),)


class Orders(models.Model):
    orderid = models.AutoField(db_column='OrderId', primary_key=True)  # Field name made lowercase.
    status = models.CharField(db_column='Status', max_length=45, blank=True, null=True)  # Field name made lowercase.
    fkuserid = models.ForeignKey('User', models.DO_NOTHING, db_column='FkUserId', blank=True, null=True)  # Field name made lowercase.
    dateoforder = models.CharField(db_column='DateOfOrder', max_length=100, blank=True, null=True)  # Field name made lowercase.
    orderdate = models.DateTimeField(db_column='OrderDate', blank=True, null=True)  # Field name made lowercase.
    dataoforder = models.CharField(db_column='DataOfOrder', max_length=255, blank=True, null=True)  # Field name made lowercase.
    availability = models.TextField(db_column='Availability', blank=True, null=True)  # Field name made lowercase. This field type is a guess.

    class Meta:
        managed = False
        db_table = 'Orders'


class Payment(models.Model):
    paymentid = models.AutoField(db_column='PaymentId', primary_key=True)  # Field name made lowercase.
    mode = models.CharField(db_column='Mode', max_length=45, blank=True, null=True)  # Field name made lowercase.
    amount = models.FloatField(db_column='Amount', blank=True, null=True)  # Field name made lowercase.
    order_orderid = models.ForeignKey(Orders, models.DO_NOTHING, db_column='Order_OrderId', blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'Payment'


class Payment2(models.Model):
    paymentid = models.IntegerField(db_column='PaymentId', blank=True, null=True)  # Field name made lowercase.
    mode = models.TextField(db_column='Mode', blank=True, null=True)  # Field name made lowercase.
    amount = models.IntegerField(db_column='Amount', blank=True, null=True)  # Field name made lowercase.
    orderid = models.IntegerField(db_column='OrderId', blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'Payment2'


class Product(models.Model):
    productid = models.AutoField(db_column='ProductId', primary_key=True)  # Field name made lowercase.
    cost = models.IntegerField(db_column='Cost', blank=True, null=True)  # Field name made lowercase.
    productdescription = models.CharField(db_column='ProductDescription', max_length=2000, blank=True, null=True)  # Field name made lowercase.
    sellingprice = models.IntegerField(db_column='SellingPrice', blank=True, null=True)  # Field name made lowercase.
    productname = models.CharField(db_column='ProductName', max_length=2000, blank=True, null=True)  # Field name made lowercase.
    availability = models.TextField(db_column='Availability', blank=True, null=True)  # Field name made lowercase. This field type is a guess.
    productcode = models.CharField(db_column='ProductCode', max_length=100, blank=True, null=True)  # Field name made lowercase.
    quantity = models.IntegerField(db_column='Quantity', blank=True, null=True)  # Field name made lowercase.
    categoryid = models.ForeignKey(Categories, models.DO_NOTHING, db_column='CategoryId', blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'Product'


class User(models.Model):
    userid = models.AutoField(db_column='UserId', primary_key=True)  # Field name made lowercase.
    email = models.CharField(db_column='Email', max_length=200, blank=True, null=True)  # Field name made lowercase.
    contactfirstname = models.CharField(db_column='ContactFirstName', max_length=200, blank=True, null=True)  # Field name made lowercase.
    contactlastname = models.CharField(db_column='ContactLastName', max_length=200, blank=True, null=True)  # Field name made lowercase.
    phone = models.CharField(db_column='Phone', max_length=100, blank=True, null=True)  # Field name made lowercase.
    addressline1 = models.CharField(db_column='AddressLine1', max_length=500, blank=True, null=True)  # Field name made lowercase.
    addressline2 = models.CharField(db_column='AddressLine2', max_length=500, blank=True, null=True)  # Field name made lowercase.
    city = models.CharField(db_column='City', max_length=100, blank=True, null=True)  # Field name made lowercase.
    state = models.CharField(db_column='State', max_length=100, blank=True, null=True)  # Field name made lowercase.
    postalcode = models.IntegerField(db_column='PostalCode', blank=True, null=True)  # Field name made lowercase.
    name = models.CharField(db_column='Name', max_length=100, blank=True, null=True)  # Field name made lowercase.
    country = models.CharField(db_column='Country', max_length=100, blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'User'


class Loginformation(models.Model):
    idloginformation = models.AutoField(db_column='idLogInformation', primary_key=True)  # Field name made lowercase.
    url = models.CharField(db_column='Url', max_length=45, blank=True, null=True)  # Field name made lowercase.
    ipaddress = models.CharField(db_column='IpAddress', max_length=45, blank=True, null=True)  # Field name made lowercase.
    executiontime = models.CharField(db_column='ExecutionTime', max_length=45, blank=True, null=True)  # Field name made lowercase.
    dateofrequest = models.CharField(db_column='DateOfRequest', max_length=100, blank=True, null=True)  # Field name made lowercase.
    httpstatus = models.CharField(db_column='HttpStatus', max_length=45, blank=True, null=True)  # Field name made lowercase.
    parameters = models.CharField(db_column='Parameters', max_length=300, blank=True, null=True)  # Field name made lowercase.
    requesttype = models.CharField(db_column='RequestType', max_length=45, blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'loginformation'


class Product(models.Model):
    product_id = models.IntegerField(primary_key=True)
    availability = models.CharField(max_length=255, blank=True, null=True)
    cost = models.IntegerField()
    product_description = models.CharField(max_length=255, blank=True, null=True)
    product_name = models.CharField(max_length=255, blank=True, null=True)
    selling_price = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'product'
