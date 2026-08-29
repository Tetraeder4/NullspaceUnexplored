#version 330

uniform sampler2D InSampler;

in vec2 texCoord;

out vec4 fragColor;

layout(std140) uniform SamplerInfo {
    vec2 OutSize;
    vec2 InSize;
};

layout(std140) uniform AbborationCompositeConfig {
    float Intensity;
};

void main() {
    vec2 uv = texCoord;
    vec2 centered = uv * 2.0 - 1.0;
    float r2 = dot(centered, centered);

    float strength = Intensity * r2;
    vec2 offset = centered * strength;
    vec3 color;

    color.r = texture(InSampler, uv + offset).r;
    color.g = texture(InSampler, uv).g;
    color.b = texture(InSampler, uv - offset).b;

    fragColor = vec4(color, 1.0);
}