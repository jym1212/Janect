from transformers import GPT2LMHeadModel, GPT2Tokenizer, TextDataset, DataCollatorForLanguageModeling, Trainer, TrainingArguments

# Load GPT-2 model and tokenizer
model_name = "gpt2"  # You can try other GPT-2 variants like 'gpt2-medium'
tokenizer = GPT2Tokenizer.from_pretrained(model_name)
model = GPT2LMHeadModel.from_pretrained(model_name)

# Prepare dataset
dataset = TextDataset(
    tokenizer=tokenizer,
    file_path="your_formatted_dataset.txt",  # Replace with your dataset file
    block_size=128  # Adjust as needed
)
data_collator = DataCollatorForLanguageModeling(tokenizer=tokenizer, mlm=False)

# Fine-tuning configuration
training_args = TrainingArguments(
    output_dir="./fine-tuned-gpt2",
    overwrite_output_dir=True,
    num_train_epochs=3,
    per_device_train_batch_size=4,
    save_steps=10_000,
    save_total_limit=2,
)

trainer = Trainer(
    model=model,
    args=training_args,
    data_collator=data_collator,
    train_dataset=dataset,
)

# Start fine-tuning
trainer.train()

# Save fine-tuned model
trainer.save_model("./fine-tuned-gpt2")
