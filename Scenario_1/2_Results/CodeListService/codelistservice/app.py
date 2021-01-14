from flask import Flask, Response
import json

app = Flask(__name__)

@app.route("/")
def index():
    return("CodeListService")

@app.route("/countries")
def countries():
    f = open("code-list.json")
    data = "".join(f.readlines())
    return(Response(data, mimetype="application/json"))

if __name__ == "__main__": 
    app.run(host ='0.0.0.0', port = 5010, debug = True)  