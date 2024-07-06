import os

from openai import OpenAI
from dotenv import load_dotenv
from flask import Flask, jsonify

app = Flask(__name__)
load_dotenv()
client = OpenAI(api_key=os.environ.get("API_KEY"))
user_id = 1


@app.route('/get_word', methods=['GET'])
def get_word():
    completion = client.chat.completions.create(
      model="gpt-4-0125-preview",
      response_format={"type": "json_object"},
      messages=[
        {"role": "user", "content":
            "Give me a Turkish word, its English translation, difficulty level 1 through 5, and a breakdown of each root in the word and its translation in the format of this JSON object { name: word, english: english, difficulty: difficulty, roots: [{ name: root, english: english}]."
         # "Give me a Turkish word and its English translation in JSON like so { name: word, english: english }. Make sure the JSON can parsed by Jackson."
         }])
    print(completion.choices[0].message.content)
    print(type(completion.choices[0].message.content))
    return jsonify(completion.choices[0].message.content)


if __name__ == '__main__':
    app.run(debug=True)

