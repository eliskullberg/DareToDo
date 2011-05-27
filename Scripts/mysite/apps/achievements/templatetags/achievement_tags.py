from django import template

from achievements.models import MissionCategory
from achievements.models import UserAchievements


register = template.Library()

@register.inclusion_tag("edit_achievements.html")
def edit_achievements(user):        
    try:
        achievements = UserAchievements.objects.get(user=user)
        completed_missions = achievements.completed_missions.all()
    except UserAchievements.DoesNotExist:
        completed_missions = []
    
    return {"categories": MissionCategory.objects.all(),
            "completed_missions": completed_missions}

@register.inclusion_tag("display_achievements.html")
def display_achievements(user):
    try:
        achievements = UserAchievements.objects.get(user=user)
        completed_missions = achievements.completed_missions.all()
    except UserAchievements.DoesNotExist:
        completed_missions = []

    return {"categories": MissionCategory.objects.all(),
            "completed_missions": completed_missions,
            "user_id": user.id}

