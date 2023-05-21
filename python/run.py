from app import app, db
import os

from app import app

if __name__ == '__main__':
    with app.app_context():
        db.create_all()
        port = 5000 # int(os.environ.get("PORT", 5000))
        app.run(debug=True, host="0.0.0.0", port=port)
