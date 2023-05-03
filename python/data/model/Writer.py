from app import db
class Writer(db.Model):
    __tablename__ = "Writer"

    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String)
    penname = db.Column(db.String)
    title = db.Column(db.String)
    
