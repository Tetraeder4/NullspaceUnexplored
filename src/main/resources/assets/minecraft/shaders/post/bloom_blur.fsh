#version 330

uniform sampler2D InSampler;

in vec2 texCoord;

layout(std140) uniform SamplerInfo {
    vec2 OutSize;
    vec2 InSize;
};

layout(std140) uniform BloomBlurConfig {
    vec2 BlurDir;
    float Radius;
};

out vec4 fragColor;

void main() {
    vec2 texel = 1.0 / InSize;

    vec3 result = vec3(0.0);

    result += texture(
            InSampler,
            texCoord
    ).rgb * 0.227027;

    result += texture(
            InSampler,
            texCoord + BlurDir * texel * Radius
    ).rgb * 0.1945946;

    result += texture(
            InSampler,
            texCoord - BlurDir * texel * Radius
    ).rgb * 0.1945946;

    result += texture(
            InSampler,
            texCoord + BlurDir * texel * Radius * 2.0
    ).rgb * 0.1216216;

    result += texture(
            InSampler,
            texCoord - BlurDir * texel * Radius * 2.0
    ).rgb * 0.1216216;

    result += texture(
            InSampler,
            texCoord + BlurDir * texel * Radius * 3.0
    ).rgb * 0.054054;

    result += texture(
            InSampler,
            texCoord - BlurDir * texel * Radius * 3.0
    ).rgb * 0.054054;

    result += texture(
            InSampler,
            texCoord + BlurDir * texel * Radius * 4.0
    ).rgb * 0.016216;

    result += texture(
            InSampler,
            texCoord - BlurDir * texel * Radius * 4.0
    ).rgb * 0.016216;

    fragColor = vec4(result, 1.0);
}