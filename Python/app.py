from flask import Flask, jsonify
from flask_sqlalchemy import SQLAlchemy


app = Flask(__name__)
app.config.from_object("data.config.Config")

@app.route("/")
def hello_world():
    return jsonify(hello= "world")

db = SQLAlchemy(app)



from flask.cli import FlaskGroup
cli = FlaskGroup(app)


@app.post('/letters')
def show_post(post_id):
    # show the post with the given id, the id is an integer
    title = request.json['title']
    return f'Post {post_id}'


if __name__ == "__main__":
    cli()