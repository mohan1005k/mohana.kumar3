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
        return self.description

    class Meta:
        managed = False
        db_table = 'Categories'


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

    def __unicode__(self):
        return self.productcode

    class Meta:
        managed = False
        db_table = 'Product'

class Orders(models.Model):
    orderid = models.AutoField(db_column='OrderId', primary_key=True)  # Field name made lowercase.
    status = models.CharField(db_column='Status', max_length=45, blank=True, null=True)  # Field name made lowercase.
    fkuserid = models.ForeignKey('User', models.DO_NOTHING, db_column='FkUserId', blank=True, null=True)  # Field name made lowercase.
    dateoforder = models.CharField(db_column='DateOfOrder', max_length=100, blank=True, null=True)  # Field name made lowercase.
    orderdate = models.DateField(db_column='OrderDate', blank=True, null=True)  # Field name made lowercase.
    dataoforder = models.CharField(db_column='DataOfOrder', max_length=255, blank=True, null=True)  # Field name made lowercase.
    availability = models.TextField(db_column='Availability', blank=True, null=True)  # Field name made lowercase. This field type is a guess.

    class Meta:
        managed = False
        db_table = 'Orders'


class Brand(models.Model):
    brandname = models.CharField(db_column='BrandName', primary_key=True, max_length=100)  # Field name made lowercase.
    branddescription = models.CharField(db_column='BrandDescription', max_length=200, blank=True, null=True)  # Field name made lowercase.
    productid = models.IntegerField(db_column='ProductId')  # Field name made lowercase.

    class Meta:
        managed = False
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
        managed = False
        db_table = 'Feedback'


class OrderHasProduct(models.Model):
    id = models.AutoField(db_column='id', primary_key=True,null=False)
    orderid = models.ForeignKey('Orders', models.DO_NOTHING, db_column='OrderId')  # Field name made lowercase.
    productid = models.ForeignKey('Product', models.DO_NOTHING, db_column='ProductId')  # Field name made lowercase.
    quantityordered = models.IntegerField(db_column='QuantityOrdered', blank=True, null=True)  # Field name made lowercase.
    price = models.FloatField(db_column='Price', blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'Order_has_Product'
        unique_together = (('orderid', 'productid'),)




class Payment(models.Model):
    paymentid = models.AutoField(db_column='PaymentId', primary_key=True)  # Field name made lowercase.
    mode = models.CharField(db_column='Mode', max_length=45, blank=True, null=True)  # Field name made lowercase.
    amount = models.FloatField(db_column='Amount', blank=True, null=True)  # Field name made lowercase.
    order_orderid = models.ForeignKey(Orders, models.DO_NOTHING, db_column='Order_OrderId', blank=True, null=True)  # Field name made lowercase.

    class Meta:
        managed = False
        db_table = 'Payment'



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


class AuthGroup(models.Model):
    name = models.CharField(unique=True, max_length=80)

    class Meta:
        managed = False
        db_table = 'auth_group'


class AuthGroupPermissions(models.Model):
    group = models.ForeignKey(AuthGroup, models.DO_NOTHING)
    permission = models.ForeignKey('AuthPermission', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'auth_group_permissions'
        unique_together = (('group', 'permission'),)


class AuthPermission(models.Model):
    name = models.CharField(max_length=255)
    content_type = models.ForeignKey('DjangoContentType', models.DO_NOTHING)
    codename = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'auth_permission'
        unique_together = (('content_type', 'codename'),)


class AuthUser(models.Model):
    password = models.CharField(max_length=128)
    last_login = models.DateTimeField(blank=True, null=True)
    is_superuser = models.IntegerField()
    username = models.CharField(unique=True, max_length=30)
    first_name = models.CharField(max_length=30)
    last_name = models.CharField(max_length=30)
    email = models.CharField(max_length=254)
    is_staff = models.IntegerField()
    is_active = models.IntegerField()
    date_joined = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'auth_user'


class AuthUserGroups(models.Model):
    user = models.ForeignKey(AuthUser, models.DO_NOTHING)
    group = models.ForeignKey(AuthGroup, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'auth_user_groups'
        unique_together = (('user', 'group'),)


class AuthUserUserPermissions(models.Model):
    user = models.ForeignKey(AuthUser, models.DO_NOTHING)
    permission = models.ForeignKey(AuthPermission, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'auth_user_user_permissions'
        unique_together = (('user', 'permission'),)


class DjangoAdminLog(models.Model):
    action_time = models.DateTimeField()
    object_id = models.TextField(blank=True, null=True)
    object_repr = models.CharField(max_length=200)
    action_flag = models.SmallIntegerField()
    change_message = models.TextField()
    content_type = models.ForeignKey('DjangoContentType', models.DO_NOTHING, blank=True, null=True)
    user = models.ForeignKey(AuthUser, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'django_admin_log'


class DjangoContentType(models.Model):
    app_label = models.CharField(max_length=100)
    model = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'django_content_type'
        unique_together = (('app_label', 'model'),)


class DjangoMigrations(models.Model):
    app = models.CharField(max_length=255)
    name = models.CharField(max_length=255)
    applied = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'django_migrations'


class DjangoSession(models.Model):
    session_key = models.CharField(primary_key=True, max_length=40)
    session_data = models.TextField()
    expire_date = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'django_session'


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
