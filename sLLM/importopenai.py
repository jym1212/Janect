import openai
openai.api_key = "key_value"

prompt_text = "aaa"
response = openai.Completion.create(
	model = "text-davinci-003",
	prompt = prompt_text,
	max_tokens = 1000,
	temperature = 0
	)
return response["choices"][0]["text"]

