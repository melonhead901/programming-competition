def uniques_only(vals):
    seen = set()
    for val in vals:
        if val not in seen:
            yield val
            seen.add(val)


print(uniques_only([1, 2, 2, 1, 1, 3, 2, 1]))
