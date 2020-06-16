import sys

_, in_file, out_file = sys.argv

def transform_field(field):
    field = field.replace('"', '""')
    if ',' in field:
        field = '"{}"'.format(field)
    return field


with open(in_file, 'r') as inp:
    lines = inp.read().splitlines()
    with open(out_file, 'w') as out:
        for line in lines:
            splits = line.split("|")
            transformed = [transform_field(x) for x in splits]
            out.writelines([','.join(transformed) + "\n"])