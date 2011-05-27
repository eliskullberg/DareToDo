from django.db import models
from django.utils.translation import ugettext_lazy as _
from django.utils.encoding import smart_unicode
from django.contrib.auth.models import User

import math

class MissionCategory(models.Model):

    def __unicode__(self):
        return self.name
        
    name = models.CharField(_("name"), max_length=30, null=False, blank=False)

    
class Mission(models.Model):

    def __unicode__(self):
        return "%s => %s" % (self.category.name, self.description)

    description = models.CharField(_("description"), max_length=150, null=False, blank=False)
    category = models.ForeignKey(MissionCategory)
    points = models.IntegerField()
    
class UserAchievements(models.Model):

    user = models.OneToOneField(User)
    completed_missions = models.ManyToManyField(Mission, verbose_name="list of completed missions")
    
    @property
    def total_points(self):
        summed_points = self.completed_missions.aggregate(models.Sum('points'))['points__sum']
        return summed_points if summed_points is not None else 0

    @property
    def level(self):
        return int(math.floor(math.log(self.total_points + 1, 1.1)/4.0)) + 1