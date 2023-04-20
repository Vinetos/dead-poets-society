from app import db
class Circle(db.Model):
    __tablename__ = "Circle"

    id = db.Column("circle_id", db.Integer, primary_key=True)
    name = db.Column(db.Text)
    letters = db.relationship('Letter', secondary=letters, lazy='subquery',
        backref=db.backref('letter_id', lazy=True))
    """
    @JoinTable(
            name = "circle_writers",
            joinColumns = @JoinColumn(name = "writer_id"),
            inverseJoinColumns = @JoinColumn(name = "circle_id"))
    List<WriterModel> writers;
    lumn(name = "writer_id"),
            inverseJoinColumns = @JoinColumn(name = "circle_id"))
    List<WriterModel> writers;
    """