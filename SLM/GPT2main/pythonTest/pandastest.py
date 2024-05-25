import pandas as pd


text_snippets = df.apply(generate_cpa_text, axis=1).dropna().tolist()
text_snippets = df.apply(generate_cpa_text, axis=1).dropna().tolist()

def generate_cpa_text(row):
    first_name = row.get('First Name', '')
    last_name = row.get('Last Name', '')
    license_number = row.get('License Number', '')
    city = row.get('City', '')
    state = row.get('State', '')

    # Ensure non-empty values are used for generating text
    text_parts = [part for part in [first_name, last_name, license_number, city, state] if part]
    if text_parts:
        text = f"{' '.join(text_parts)}, a Certified Public Accountant (CPA)" 
        if license_number:
            text += f" with license number {license_number}"
        if city and state:
            text += f", is based in {city}, {state}."
        else:
            text += "."
        return text
    else:
        return None  # Return None for rows with no valid information


df = pd.read_csv('/Users/seungwonlee/Desktop/csv_viewer-master/data/csv/Washington_State_Certified_Public_Accountants.csv', header=None, names=['First Name', 'Middle Name', 'Last Name', 'Suffix', 'City', 'State', 'Country', 'License Number', 'Original Issue Date', 'Expiration Date', 'Status', 'Last Updated', 'Board Order'])

print(df.head())  # Display the first few rows to check if it's read correctly
