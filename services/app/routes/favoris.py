from fastapi import APIRouter
from ..db import db
from ..models import Favorite
import csv
import io

router = APIRouter()

@router.get("/export/favorite/csv")
async def export_favorite_list_to_csv():
    pass