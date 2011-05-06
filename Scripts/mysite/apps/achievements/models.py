﻿from django.db import models
from django.utils.translation import ugettext_lazy as _
from django.utils.encoding import smart_unicode


class MissionCategory(models.Model):

    def __unicode__(self):
        return self.name
        
    name = models.CharField(_("name"), max_length=30, null=False, blank=False)

    
class Mission(models.Model):

    def __unicode__(self):
        return "%s => %s" % (self.category.name, self.description)

    description = models.CharField(_("description"), max_length=150, null=False, blank=False)
    category = models.ForeignKey(MissionCategory)
    
    