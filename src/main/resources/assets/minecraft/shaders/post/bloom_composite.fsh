#version 330

uniform sampler2D InSampler;
uniform sampler2D BloomSampler;

in vec2 texCoord;

layout(std140) uniform SamplerInfo {
    vec2 OutSize;
    vec2 InSize;
};

layout(std140) uniform BloomCompositeConfig {
    float Intensity;
};

out vec4 fragColor;

void main() {
    vec3 original = texture(InSampler, texCoord).rgb;
    vec3 bloom = texture(BloomSampler, texCoord).rgb;

    vec3 color = original + (bloom * Intensity);

    fragColor = vec4(color, 1.0);
}