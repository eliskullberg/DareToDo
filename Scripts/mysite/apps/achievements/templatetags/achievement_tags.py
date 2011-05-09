from django import template

from achievements.models import MissionCategory


register = template.Library()


@register.inclusion_tag("achievement_ui.html")
def achievement_ui(completed_missions):
    return {"categories": MissionCategory.objects.all(),
            "completed_missions": completed_missions}

