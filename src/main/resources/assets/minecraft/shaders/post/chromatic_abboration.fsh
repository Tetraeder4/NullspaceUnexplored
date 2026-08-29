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

    // Position relative to screen center
    vec2 centered = uv * 2.0 - 1.0;

    // Radial distance, squared
    float r2 = dot(centered, centered);

    // Chromatic aberration strength
    float strength = Intensity * r2;

    // Radial distortion direction
    vec2 offset = centered * strength;

    // RGB samples
    vec3 color;

    color.r = texture(InSampler, uv + offset).r;
    color.g = texture(InSampler, uv).g;
    color.b = texture(InSampler, uv - offset).b;

    fragColor = vec4(color, 1.0);
}