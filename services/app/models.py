from pydantic import BaseModel, Field, HttpUrl
from typing import Optional, Any
from bson import ObjectId

class PyObjectId(ObjectId):
    @classmethod
    def __get_validators__(cls):
        yield cls.validate
        
    @classmethod
    def validate(cls, v: Any, field: Any):
        if not ObjectId.is_valid(v):
            raise ValueError("Invalid ObjectId")
        return ObjectId(v)
    
    @classmethod
    def __get_pydantic_json_schema__(cls, field_schema: dict[str, Any]):
        field_schema.update(type="string")
        
class Product(BaseModel):
    id: Optional[PyObjectId] = Field(alias="_id")
    name: str
    category: Optional[str] = None
    description: Optional[str] = None
    stock_quantity: int
    price: float
    image_url: Optional[str] = None
    
    class Config:
        validate_by_name = True
        json_encoders = {ObjectId: str}
        json_schema_extra = {
            "example": {
                "name": "ballon",
                "category": "basket-ball",
                "description": "ballon orange",
                "stock_quantity": 100,
                "price": 57.25,
                "image_url": "https://example.com/image.png"
            }
        }