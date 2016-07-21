from django.contrib import admin

# Register your models here.
from . import models
# from . models import Categories
# from . models import Product

admin.site.register(models.Categories)
admin.site.register(models.Product)