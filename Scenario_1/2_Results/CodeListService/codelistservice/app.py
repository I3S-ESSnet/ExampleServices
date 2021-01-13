from flask import Flask, Response
import json

app = Flask(__name__)

@app.route("/countries")
def index():
    f = open("code-list.json")
    data = "".join(f.readlines())
    return(Response(data, mimetype="application/json"))
