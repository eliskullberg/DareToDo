import models
from django.contrib import admin

class MissionInline(admin.TabularInline):
    model = models.Mission

class MissionCategoryAdmin(admin.ModelAdmin):
    inlines = [
        MissionInline,
    ]

admin.site.register(models.MissionCategory, MissionCategoryAdmin)
