# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey has `on_delete` set to the desired behavior.
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from __future__ import unicode_literals

from django.db import models



class Categories(models.Model):
    description = models.CharField(db_column='Description', max_length=45, blank=True, null=True)  # Field name made lowercase.

    def __unicode__(self):
        return self.id

    def __getitem__(self):
        return self.id

    class Meta:
        managed = True
        db_table = 'Categories'


class User(models.Model):
    userid = models.AutoField(db_column='UserId', primary_key=True)  # Field name made lowercase.
    email = models.CharField(db_column='Email', max_length=200, blank=True, null=True)  # Field name made lowercase.
    contactfirstname = models.CharField(db_column='ContactFirstName', max_length=200, blank=True, null=True)  # Field name made lowercase.
    contactlastname = models.CharField(db_column='ContactLastName', max_length=200, blank=True, null=True)  # Field name made lowercase.
    phone = models.CharField(db_column='Phone', max_length=100, blank=True, null=True)  # Field name made lowercase.
    address = models.CharField(db_column='AddressLine1', max_length=500, blank=True, null=True)  # Field name made lowercase.
    addressline2 = models.CharField(db_column='AddressLine2', max_length=500, blank=True, null=True)  # Field name made lowercase.
    city = models.CharField(db_column='City', max_length=100, blank=True, null=True)  # Field name made lowercase.
    state = models.CharField(db_column='State', max_length=100, blank=True, null=True)  # Field name made lowercase.
    postalcode = models.IntegerField(db_column='PostalCode', blank=True, null=True)  # Field name made lowercase.
    username = models.CharField(db_column='Name', max_length=100, blank=True, null=True)  # Field name made lowercase.
    country = models.CharField(db_column='Country', max_length=100, blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = True
        db_table = 'User'


class Product(models.Model):
    id = models.AutoField(db_column='ProductId', primary_key=True)  # Field name made lowercase.
    price = models.IntegerField(db_column='Cost', blank=True, null=True)  # Field name made lowercase.
    description = models.CharField(db_column='ProductDescription', max_length=2000, blank=True, null=True)  # Field name made lowercase.
    sellingprice = models.IntegerField(db_column='SellingPrice', blank=True, null=True)  # Field name made lowercase.
    productname = models.CharField(db_column='ProductName', max_length=2000, blank=True, null=True)  # Field name made lowercase.
    availability = models.BooleanField(db_column='Availability', blank=True, default=True)  # Field name made lowercase. This field type is a guess.
    code = models.CharField(db_column='ProductCode', max_length=100, blank=True, null=True)  # Field name made lowercase.
    quantity = models.IntegerField(db_column='Quantity', blank=True, null=True)  # Field name made lowercase.
    categoryid = models.ForeignKey(Categories, models.DO_NOTHING, db_column='CategoryId', blank=True, null=True)  # Field name made lowercase.

    def __unicode__(self):
        return self.id

    class Meta:
        managed = True
        db_table = 'Product'

class Orders(models.Model):
    id = models.AutoField(db_column='OrderId', primary_key=True)  # Field name made lowercase.
    status = models.CharField(db_column='Status', max_length=45, default='CREATED',blank=True)  # Field name made lowercase.
    fkuserid = models.ForeignKey('User', models.DO_NOTHING, db_column='FkUserId', blank=True, null=True)  # Field name made lowercase.
    orderdate = models.DateField(db_column='OrderDate', blank=True, null=True)  # Field name made lowercase.
    availability = models.BooleanField(db_column='Availability', blank=True, default=True)  # Field name made lowercase. This field type is a guess.

    class Meta:
        managed = True
        db_table = 'Orders'


class Brand(models.Model):
    brandname = models.CharField(db_column='BrandName', primary_key=True, max_length=100)  # Field name made lowercase.
    branddescription = models.CharField(db_column='BrandDescription', max_length=200, blank=True, null=True)  # Field name made lowercase.
    productid = models.IntegerField(db_column='ProductId')  # Field name made lowercase.

    class Meta:
        managed = True
        db_table = 'Brand'



class Feedback(models.Model):
    description = models.CharField(db_column='Description', max_length=300, blank=True, null=True)  # Field name made lowercase.
    email = models.CharField(db_column='Email', max_length=45, blank=True, null=True)  # Field name made lowercase.
    type = models.CharField(db_column='Type', max_length=45, blank=True, null=True)  # Field name made lowercase.
    feedbackdate = models.DateTimeField(db_column='FeedbackDate', blank=True, null=True)  # Field name made lowercase.
    status = models.CharField(db_column='Status', max_length=45, blank=True, null=True)  # Field name made lowercase.
    userid = models.ForeignKey('User', models.DO_NOTHING, db_column='UserId', blank=True, null=True)  # Field name made lowercase.
    feedbackid = models.AutoField(db_column='FeedbackId', primary_key=True)  # Field name made lowercase.

    class Meta:
        managed = True
        db_table = 'Feedback'


class OrderHasProduct(models.Model):
    id = models.AutoField(db_column='id', primary_key=True,null=False)
    orderid = models.ForeignKey('Orders', models.DO_NOTHING, db_column='OrderId')  # Field name made lowercase.
    productid = models.ForeignKey('Product', models.DO_NOTHING, db_column='ProductId')  # Field name made lowercase.
    quantityordered = models.IntegerField(db_column='QuantityOrdered', blank=True, null=True)  # Field name made lowercase.
    price = models.FloatField(db_column='Price', blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = True
        db_table = 'Order_has_Product'
        unique_together = (('orderid', 'productid'),)




class Payment(models.Model):
    paymentid = models.AutoField(db_column='PaymentId', primary_key=True)  # Field name made lowercase.
    mode = models.CharField(db_column='Mode', max_length=45, blank=True, null=True)  # Field name made lowercase.
    amount = models.FloatField(db_column='Amount', blank=True, null=True)  # Field name made lowercase.
    order_orderid = models.ForeignKey(Orders, models.DO_NOTHING, db_column='Order_OrderId', blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = True
        db_table = 'Payment'



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
        managed = True
        db_table = 'loginformation'
