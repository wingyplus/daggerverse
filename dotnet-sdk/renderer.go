package main

import (
	"context"
	"fmt"
	"strings"

	"github.com/iancoleman/strcase"
)

type EnumValue struct {
	Name        string
	Description string
}

type Enum struct {
	Name        string
	Description string
	Values      []EnumValue
}

type EnumRenderer struct{}

func (r *EnumRenderer) Render(enum *Enum, builder *strings.Builder) {
	RenderDoc2(builder, enum.Description)
	nl(builder)
	builder.WriteString(fmt.Sprintf("public enum %s {", enum.Name))
	nl(builder)
	for _, v := range enum.Values {
		RenderDoc3(builder, v.Description, 1)
		nl(builder)
		RenderIndent2(builder, 1)
		builder.WriteString(v.Name)
		builder.WriteRune(',')
		nl(builder)
	}
	nl(builder)
	builder.WriteRune('}')
}

func (r *EnumRenderer) RenderMany(ctx context.Context, builder *strings.Builder, enums []CodegenType) error {
	for _, enum := range enums {
		e, err := r.IntoEnum(ctx, enum)
		if err != nil {
			return err
		}
		r.Render(e, builder)
		nl(builder)
		nl(builder)
	}
	return nil
}

func (r *EnumRenderer) IntoEnum(ctx context.Context, enum CodegenType) (*Enum, error) {
	name, err := enum.Name(ctx)
	if err != nil {
		return nil, err
	}
	desc, err := enum.Description(ctx)
	if err != nil {
		return nil, err
	}
	enumValues, err := enum.EnumValues(ctx)
	if err != nil {
		return nil, err
	}

	e := &Enum{
		Name:        name,
		Description: desc,
		Values:      make([]EnumValue, len(enumValues)),
	}

	for i, ev := range enumValues {
		name, err := ev.Name(ctx)
		if err != nil {
			return nil, err
		}
		desc, err := ev.Description(ctx)
		if err != nil {
			return nil, err
		}
		e.Values[i] = EnumValue{Name: name, Description: desc}
	}

	return e, nil
}

type InputField struct {
	Name        string
	Description string
	Type        *CodegenTypeRef
}

type InputValue struct {
	Name        string
	Description string
	Fields      []*InputField
}

type InputValueRenderer struct{}

func (r *InputValueRenderer) TypeToString(ctx context.Context, type_ *CodegenTypeRef) (string, error) {
	isOptional, err := type_.IsOptional(ctx)
	if err != nil {
		return "", err
	}
	isLeaf, err := type_.IsLeaf(ctx)
	if err != nil {
		return "", err
	}
	if isLeaf {
		if isOptional {
			name, err := type_.Name(ctx)
			if err != nil {
				return "", err
			}
			return normalizeType(name) + "?", err
		} else {
			name, err := type_.OfType().Name(ctx)
			if err != nil {
				return "", err
			}
			return normalizeType(name), err
		}
	}

	return "", nil
}

func (r *InputValueRenderer) Render(ctx context.Context, iv *InputValue, builder *strings.Builder) error {
	RenderDoc2(builder, iv.Description)
	nl(builder)
	builder.WriteString(fmt.Sprintf("public struct %s {", iv.Name))
	nl(builder)
	for _, field := range iv.Fields {
		type_, err := r.TypeToString(ctx, field.Type)
		if err != nil {
			return err
		}
		RenderDoc3(builder, field.Description, 1)
		nl(builder)
		RenderIndent2(builder, 1)
		builder.WriteString(fmt.Sprintf("public %s %s { get; set; }", type_, strcase.ToCamel(field.Name)))
		nl(builder)
	}
	builder.WriteRune('}')
	return nil
}

func (r *InputValueRenderer) RenderMany(ctx context.Context, builder *strings.Builder, inputs []CodegenType) error {
	for _, input := range inputs {
		iv, err := r.IntoInputValue(ctx, input)
		if err != nil {
			return err
		}

		if err := r.Render(ctx, iv, builder); err != nil {
			return err
		}
		nl(builder)
		nl(builder)
	}
	return nil
}

func (r *InputValueRenderer) IntoInputValue(ctx context.Context, input CodegenType) (*InputValue, error) {
	name, err := input.Name(ctx)
	if err != nil {
		return nil, err
	}

	desc, err := input.Description(ctx)
	if err != nil {
		return nil, err
	}

	fields, err := input.InputFields(ctx)
	if err != nil {
		return nil, err
	}

	iv := &InputValue{Name: name, Description: desc, Fields: make([]*InputField, len(fields))}

	for i, field := range fields {
		inf, err := r.IntoInputField(ctx, field)
		if err != nil {
			return nil, err
		}
		iv.Fields[i] = inf
	}

	return iv, nil
}

func (r *InputValueRenderer) IntoInputField(ctx context.Context, field CodegenInputValue) (*InputField, error) {
	name, err := field.Name(ctx)
	if err != nil {
		return nil, err
	}

	desc, err := field.Description(ctx)
	if err != nil {
		return nil, err
	}

	return &InputField{Name: name, Description: desc, Type: field.Type()}, nil
}