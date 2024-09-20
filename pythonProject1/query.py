import os
from urllib import request

from openai import OpenAI
from dotenv import load_dotenv
from flask import Flask, jsonify
from pydantic import json

app = Flask(__name__)
load_dotenv()
client = OpenAI(api_key=os.environ.get("API_KEY"))
user_id = 1

@app.route('/receive_data', methods=['POST'])
def receive_data():
    try:
        print("HERE")
        data = request.get_data()  # Get the data sent from the Spring app
        if not data:
            raise ValueError("No data received")
        data_str = data.decode('utf-8')  # Decode the byte data to string
        print("Received data from Spring app:", data_str)

        # Optionally, you can validate the received data here
        # For example, ensure it's valid JSON:
        json_data = json.loads(data_str)
        # Perform any necessary processing on the data
        # ...

        return jsonify({"message": "Data received successfully!"})
    except json.JSONDecodeError as je:
        # Handle JSON decoding errors gracefully
        app.logger.error(f"Invalid JSON data received: {je}")
        return jsonify({"error": "Invalid JSON data format"}), 400  # Bad Request
    except ValueError as ve:
        # Handle missing data error
        app.logger.error(f"No data received: {ve}")
        return jsonify({"error": "No data provided"}), 400
    except Exception as e:
        # Catch any other unforeseen exceptions
        app.logger.error(f"Unexpected error in receive_data: {e}")
        return jsonify({"error": "Internal Server Error"}), 500

@app.route('/get_word', methods=['GET'])

def get_word():
    try:
        user_words = [
            {"name": "magányos", "english": "lonely", "flagged": False, "notes": ""},
            {"name": "kézfej", "english": "knuckles", "flagged": False, "notes": ""},
            {"name": "folyadék", "english": "liquid", "flagged": False, "notes": ""}
        ]

        # Prepare the context
        context = "The user is learning Hungarian. Here are some words they've already learned:\n"
        for word in user_words:
            context += f"- {word['name']} ({word['english']})\n"

        # Prepare the messages for the API call
        messages = [
            {"role": "system", "content": context},
            {"role": "user",
             "content": "Give me a Turkish word, its English translation, difficulty level 1 through 5, and a breakdown of each root in the word and its translation. The word should be different from the ones the user already knows and every so often be related by roots or origin. Provide the response in the format of this JSON object: { \"name\": \"word\", \"english\": \"english\", \"difficulty\": difficulty, \"roots\": [{ \"name\": \"root\", \"english\": \"english\"}]}. Ensure the response is valid JSON."}
        ]

        completion = client.chat.completions.create(
          model="gpt-4-0125-preview",
          response_format={"type": "json_object"},
          messages=messages
        )
          # messages=[
          #   {"role": "user", "content":
          #       "Give me a Turkish word, its English translation, difficulty level 1 through 5, and a breakdown of each root in the word and its translation in the format of this JSON object { name: word, english: english, difficulty: difficulty, roots: [{ name: root, english: english}]."
          #    # "Give me a Turkish word and its English translation in JSON like so { name: word, english: english }. Make sure the JSON can parsed by Jackson."
          #    }])
        #     {"role": "user", "content": "As JSON give me a Hungarian word, its English translation and a breakdown of each root in the word and its translation in JSON in the format of this object { name: word, english: english, roots: [{ name: root, english: english}]."}])
        # {"role": "user",
        #  "content": "As JSON give me a Turkish word, its English translation and a breakdown of each root in the word in the format of this object { name: word, english: english, difficulty: difficulty, root-words: [root: { name: root, english: english}, position: position in the word, mutation: any accents or mutations on the root]."}])
        print(completion.choices[0].message.content)
        print(type(completion.choices[0].message.content))
        return jsonify(completion.choices[0].message.content)
    except Exception as e:
        # Catch any general exceptions that occur during the API call
        app.logger.error(f"Error occurred in get_word: {e}")
        return jsonify({"error": "Internal Server Error"}), 500


if __name__ == '__main__':
    app.run(debug=True)

